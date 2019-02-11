
var object = $("#search")[0];
object.onkeypress  = function(){
    getItems(this.value);
};

function getItem (itemName, handle) {
    var url = "/api/EFT/getItem?item=" + itemName;
    console.log(url);

     $.ajax({
          dataType: 'json',
          url: url,
          cache: false,
          success: function(data){
               handle(data);
          }
     });
};

function updateExplorer (item) {
   updateView(item);
   var htmlStuff = buildItems(item);
   $("#explore")[0].innerHTML = htmlStuff;
}

function buildItems(item) {
    var string = addItemName(item.Item)
    var part_keys = Object.keys(item.parts);
    for (var index=0, len=part_keys.length ; index < len ; index++ ){
        string = string + addTitle(part_keys[index]);
        var itemList = item.parts[part_keys[index]];
        for (var i=0, length=itemList.length ; i < length ; i++ ){
             string = string + addPart(itemList[i]);
        }
    }
    return string
}

function addItemName(name){
    return "<div class=\"part-header\">" + name + "</div>";
}

function addTitle(section){
    return "<div class=\"title\">" +  section + "</div>";
}

function addDetail(key, value){
    return "<div class=\"detail\">" +  key + " = " + value + "</div>";
}

function addPart(part){
    return  "<div id=\""+ part + "\" class=\"part view\">" + part + "</div>";
}

function getItems (filter) {
     $.ajax({
          dataType: 'json',
          url: "/api/EFT/availableItems.json",
          cache: false,
          success: function(data){
                printAvalibleItems (filter, data);
          }
     });
};
$(document).on("click", "div.button" , function() {
       $("#results")[0].style.display = "none";
       var item = this.getAttribute("id")
       getItem (item , updateExplorer);

});

function updateView(jsonItem){
    $("#sample-view")[0].innerHTML = jsonItem.details.title;

    var innerHTML = addTitle(jsonItem.details.title);

    var part_keys = Object.keys(jsonItem.details);
    for (var index=0, len=part_keys.length ; index < len ; index++ ){
        if(!part_keys[index].includes("title")){
            innerHTML = innerHTML + addDetail(part_keys[index], jsonItem.details[part_keys[index]])
        }
    }
    innerHTML = innerHTML + addButton (jsonItem.details.title, jsonItem.Item);
     $("#sample-view")[0].innerHTML = innerHTML;
}

$(document).on("click", "div.view" , function() {
    var item = this.getAttribute("id")
    getItem (item , updateView)
});

function printAvalibleItems(filer, results){
    filter = filer.toLowerCase();
    var domHtmlResults = $("#results")[0];
    domHtmlResults.style.display = "block";
    domHtmlResults.innerHTML = "";

    var fileNames =  results.fileNames;
    var printResults = "";
    var max = 10;
    for (var len =  fileNames.length, index = 0 ; index < len ; index++ ){
        if(fileNames[index].toLowerCase().includes(filer)){
            max = max -1;
            var name = fileNames[index];
            printResults =  printResults + addButton(name, name) ;
            if(max==0){
                index=len;
            }
        }
    }
    domHtmlResults.innerHTML = printResults;

}

function addButton (text, id) {
    return "<div class=\"button\" id=\""+ id +"\">" + text +"</div>";
}

//$( document ).ready(function() {
//    console.log('test');
//});