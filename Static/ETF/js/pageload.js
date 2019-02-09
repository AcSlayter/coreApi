
var object = $("#search")[0];
object.onkeypress  = function(){
    console.log(this.value);
    getItems(this.value);
};

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
    var playerName = this.getAttribute("id")
    console.log(playerName);
});

function printAvalibleItems(filer, results){
    filter = filer.toLowerCase();
    var domHtmlResults = $("#results")[0];
    domHtmlResults.html = "";

    var fileNames =  results.fileNames;
    var printResults = "";
    for (var len =  fileNames.length, index = 0 ; index < len ; index++ ){
        if(fileNames[index].toLowerCase().includes(filer)){
            var name = fileNames[index];
            printResults =  printResults + addButton(name, name) ;
        }
    }
    console.log(printResults)
    domHtmlResults.innerHTML = printResults;

}

function addButton (text, id) {
    return "<div class=\"button\" id=\""+ id +"\">" + text +"</div>";
}

$( document ).ready(function() {
    console.log('test');
});