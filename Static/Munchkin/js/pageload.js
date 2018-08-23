function printResults(data){
    var html_List = "";
    for ( var index = 0, len = data.length ; index < len ; index++ ){
        html_List =  html_List + addBox(data[index]);
    }
    $("#player-list").html(html_List);
}

function addBox(player){
    var upDownControls = "<div id=\"up\" name=\""+ player.name + "\"> UP </div> <div id=\"down\" name=\""+ player.name + "\">DOWN</div>";
    var playerName = "<div class=\"name\"> " + player.name + "</div>"
    var playerLevel = "<div class=\"level\"> " + player.level + "</div>"

     return "<div class=\"player\">" +playerName  + playerLevel+ upDownControls + "</div>";
}

function GetPlayer () {
    var data_results;

    GetPlayer.prototype.getPlayerList = function(callback) {
         $.ajax({
              dataType: 'json',
              url: "/api/Munchkin/getUserData.json",
              cache: false,
              success: function(data){
                    callback( data );
                    data_results = data;
              }
         });
    };
}

function AddPlayer () {
    var data_results;

    AddPlayer.prototype.addplayer = function(callback,playerName) {
         $.ajax({
              dataType: 'json',
              url: "/api/Munchkin/addPlayer/"+playerName,
              cache: false,
              success: function(data){
                    callback( data );
                    data_results = data;
              }
         });
    };
}

 $("#submit").click(function(){
        var playerName  = $("#name").val();
        var addPlayer = new AddPlayer();
        addPlayer.addplayer(console.log, playerName );

        var getPlayList = new GetPlayer ();
        getPlayList.getPlayerList(printResults);
        $("#name").val("");
});

$("#clear").click(function(){
    clearLevel ();
});

function clearLevel () {

    $.ajax({
      dataType: 'json',
      url: "/api/Munchkin/clearAll",
      cache: false,
      success: function(data){
           var getPlayList = new GetPlayer ();
           getPlayList.getPlayerList(printResults);
      }
    });
}

function addLevel (name, level) {
    console.log(name + " " + level);
    $.ajax({
      dataType: 'json',
      url: "/api/Munchkin/levelUp/" + name + "/" + level,
      cache: false,
      success: function(data){
           var getPlayList = new GetPlayer ();
           getPlayList.getPlayerList(printResults)
      }
    });
}

$(document).on("click", "div#up" , function() {
    var playerName = this.getAttribute("name")
    addLevel (playerName, 1);
});
$(document).on("click", "div#down" , function() {
    var playerName = this.getAttribute("name")
    addLevel (playerName, -1);

});

$( document ).ready(function() {
    var getPlayList = new GetPlayer ();
    getPlayList.getPlayerList(printResults)
});