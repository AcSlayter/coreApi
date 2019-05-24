/////// SEARCH CLICK /////////
 $("#search")[0].onkeypress  = function(){
    getItems(this.value);
};

function getItems (filter) {
     $.ajax({
          dataType: 'json',
          url: "/api/EFT/availableItems.json",
          cache: false,
          success: function(data){
                printAvailableItems (filter, data);
          }
     });
};

function printAvailableItems(filer, results){
    filer = filer.toLowerCase();

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

/////////BUTTON CLICK ///////////
$(document).on("click", "div.button" , function() {
       $("#results")[0].style.display = "none";
       var item = this.getAttribute("id")
       getItem (item , updateExplorer);
       addHistoryItem(item);
});

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
   var htmlStuff = buildItems(item);
   $("#explore")[0].innerHTML = htmlStuff;
}

function buildItems(item) {
    var string = addItemName(item.details.title)
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

function addPart(part){
    return  "<div id=\""+ part + "\" class=\"part view\">" + part + "</div>";
}

////// BUILD OUT WEAPON //////
$(document).on("click", "div.view" , function() {
    var item = this.getAttribute("id");
    getItem (item , updateExplorer);
    addHistoryItem(item)
});

////// GO BACK TO WEAPON //////
$(document).on("click", "div.hist-nav" , function() {
    var item = this.getAttribute("id");
    getItem (item , updateExplorer);
});


$(document).on("click", "div.clear-history-button" , function() {
    $("#history-bar")[0].innerHTML = "<div id=\"clear-history\" class=\"clear-history-button\"> clear </div>";
});


////// BUILD OUT HISTORY //////
function addingLink(part){
    return  "<div id=\""+ part + "\" class=\"hist-nav\">" + part + "</div>";
}

function addHistoryItem(value){
    var innerHTML = $("#history-bar")[0].innerHTML + addingLink(value) ;
    $("#history-bar")[0].innerHTML = innerHTML;
}
// add a history nav bar
// add an add button
// add back button
// add a new view


