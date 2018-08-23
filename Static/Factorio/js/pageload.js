
function ItemRecipe(){
    var localHash = {},
        scheduler = new Scheduler() ;


    function addDataToLocalData (data){
        if(localHash[data.name] == null){
            localHash[data.name] = data;
        }
    } ;

    ItemRecipe.prototype.getHash = function ()  {
        return localHash;
    }

    ItemRecipe.prototype.isDone = function (function_call, arrayList)  {
        scheduler.isComplete(function_call, arrayList);
    }

    ItemRecipe.prototype.getIteRecipe = function( item, callback) {
        if( localHash[item] == null ){
            scheduler.increase();
            var generate_url = "/api/Factorio/getItemRecipe/" + item ;
            $.ajax({
              dataType: 'json',
              url: generate_url,
              cache: false,
              success: function(data){
                     addDataToLocalData( data );
                     scheduler.decrease();
                    if(callback != undefined) {
                        callback ( data );
                    }
              }
            });
        } else {
             if(callback != null) {
                callback ( localHash[item] );
             }
        }
    };

}


function ItemNames () {
    var data_results;
    var currentSelection = 0;

    ItemNames.prototype.getIteList = function(callback) {
         $.ajax({
              dataType: 'json',
              url: "/api/Factorio/getNames.json",
              cache: false,
              success: function(data){
                    callback( data );
                    data_results = data;
              }
         });
    };


}
function BreakDown () {

    BreakDown.prototype.getBreakDown = function(item, amount, second, callback) {
        var url = "/api/Factorio/getBreakDown?item=" + item +"&amount=" + amount +"&second="+ second;

        $.ajax({
              dataType: 'json',
              url: url,
              cache: false,
              success: function( data){
                    callback( data );
              },
              error:function (xhr, ajaxOptions, thrownError) {
                    console.log(xhr.status);
                    console.log(thrownError);
              }
         });
    };
}

function UiInteractions(){
    var item_list_dropdown = [];
    var itemRecipe = new ItemRecipe();

    UiInteractions.prototype.populateItemDropdown = function  (allItemHash){
      var id = 0, name = "select";
            $("#item-list").append("<option value='" + id + "'>" + name + "</option>");

            item_list_dropdown = allItemHash.name_key;
            var value = 1
            for (var index = 0, len = item_list_dropdown.length ; index < len ; index ++ ){
                value = index + 1;
                $("#item-list").append("<option value='" + value + "'>" + item_list_dropdown[index] + "</option>");
            }
    }

    $( "#item-list" ).change(function() {
            var currentSelection = this.selectedIndex ;
            if(currentSelection == 0 ){
                 $("#selected-item-details").hide();
            } else if (item_list_dropdown.length >= currentSelection ) {
                itemRecipe.getIteRecipe( item_list_dropdown[currentSelection-1] , updateSelectedItem);
            }
    });

    $("#submit").click(function(){
        var currentSelection = $("#item-list").val(),
            itemName = item_list_dropdown[currentSelection-1];
            second = $("#second").val(),
            amount = $("#amount").val();

            var breakDown = new BreakDown();
            breakDown.getBreakDown(itemName, amount, second, displayPageDetails);
            $("#item-amount-list").show();
    });

    function displayPageDetails (data) {
        $("#item-amount-list").html(buildHTMLObject(data))
    }

    function buildHTMLObject(data){
        if( data == null ) {
            return "";
        }
        var item = "<div class=\"item\">" + data.name + " - " + data.amount ;


        for (var index = 0, length = data.need.length ; index < length ; index++ ) {
            item = item + buildHTMLObject(data.need[index]);
        }

        item = item + "</div>" ;
        return item;
    }

    function updateSelectedItem (data) {
        var name = data.name,
            time = data.time,
            amount = data.amount,
            itemList = data.itemlist;

        $("#item-name").text(name);
        $("#craft-time").text(time + " seconds");
        $("#craft-amount").text(amount + " items");


        $("#recipe-list").html("");
        for ( var index=0, len = itemList.length ; index < len ; index++ ) {
            $("#recipe-list").append("<div class=\"recipe-item\">" + itemList[index].item + "</div>");
            $("#recipe-list").append(" <div class=\"recipe-amount\">" + itemList[index].count  + "</div>");
        }

        $("#results").show();
        $("#rate-checker").show()

        getAllItems(itemList);
        itemRecipe.isDone(displayTree , [itemList]);
    }

    function displayTree(params){
        var hash = itemRecipe.getHash();
        var mainList =  $("#support-list");
        mainList.html("");


       for ( var index = 0 , len = params[0].length ; index < len ; index++){
            addItem( hash[params[0][index].item] , mainList);

       }

    }

    function addItem(item, mainList){
        var name = item.name,
            time = item.time,
            amount = item.amount;

        mainList.append("<div class=\"support\"> <div class=\"title center\">" + name + " </div> <div class=\"craft-details\"> <div class=\"craft-time\">" + time + " seconds </div><div class=\"craft-amount\">" + amount +" items</div></div></div>");
    }

    function getAllItems(itemList){
        for ( var index=0, len= itemList.length ; index < len ; index++   ) {
            itemRecipe.getIteRecipe( itemList[index].item, null) ;
        }
    }
}

function Scheduler () {
    var active = 0;

    Scheduler.prototype.increase = function (){
        active = active + 1;
    }

    Scheduler.prototype.decrease = function (){
        active = active - 1 ;
    }

     Scheduler.prototype.isComplete = function (function_call, params){
       stayAlive(function_call, params);
    }

    function stayAlive(nextAction, params){
        if(active == 0 ){
            nextAction(params);
        }else {
            setTimeout(function() {
                stayAlive(nextAction, params);
                }, 100);
            }

    }

}


$( document ).ready(function() {
    var itemNames = new ItemNames();
    var uiInteractions = new UiInteractions();

    //pageload Call
    itemNames.getIteList(uiInteractions.populateItemDropdown);
});