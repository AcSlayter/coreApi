var key = "MP-133_12ga_shotgun";

function setMainItem (key) {
     var ajax_url = "/api/EFT2/item/details/" + cleanKey(key);
     console.log(ajax_url);
     $.ajax({
          dataType: 'json',
          url: ajax_url,
          cache: false,
          success: function(data){
             document.getElementById("title").innerHTML  = data.title;
             document.getElementById("ergonomics").innerHTML  = data.ergonomics;
             document.getElementById("accuracy").innerHTML  = data.accuracy;
             document.getElementById("recoil").innerHTML  = data.recoil;
             document.getElementById("soldBy").innerHTML  = data.soldBy;
          }
     });
};

function getSlotTitle(slotTitle){
    var newDiv = document.createElement("div");

    var attclass = document.createAttribute("class");
    attclass.value = "slot-title";

    newDiv.innerHTML = slotTitle;
    newDiv.setAttributeNode(attclass);

    return newDiv
}

function getSlotOptions(slotTitle){
    var newDiv = document.createElement("div");
    var attId = document.createAttribute("id");
    attId.value = slotTitle;

    newDiv.setAttributeNode(attId);

    return newDiv;
}

function addItemSlotBox(itemTitle){
    var newDiv = document.createElement("div");

    var attClass = document.createAttribute("class");
    attClass.value = "slot";

    newDiv.setAttributeNode(attClass);
    newDiv.appendChild(getSlotTitle(itemTitle));
    newDiv.appendChild(getSlotOptions(itemTitle));

    document.getElementById("main").appendChild(newDiv);
}

function setTmpItem (key) {
     var ajax_url = "/api/EFT2/item/details/" + cleanKey(key);
     console.log(ajax_url);
     $.ajax({
          dataType: 'json',
          url: ajax_url,
          cache: false,
          success: function(data){
             document.getElementById("tmp-ergonomics").innerHTML  = data.ergonomics;
             document.getElementById("tmp-accuracy").innerHTML  = data.accuracy;
             document.getElementById("tmp-recoil").innerHTML  = data.recoil;
             document.getElementById("tmp-soldBy").innerHTML  = data.soldBy;
          }
     });
};

function test(key){
    var x = event.clientX;     // Get the horizontal coordinate
    var y = event.clientY
    console.log(key + " x:" + x + " y:" + y);
    var tmpDetails = document.getElementById("tmp-details");

    var doc = document.documentElement;
    var left = (window.pageXOffset || doc.scrollLeft) - (doc.clientLeft || 0);
    var top = (window.pageYOffset || doc.scrollTop)  - (doc.clientTop || 0);

    tmpDetails.style.top = y + top;
    tmpDetails.style.left = x + left ;
    tmpDetails.style.display = "block";

    setTmpItem (key)

}

function clear(){
    var tmpDetails = document.getElementById("tmp-details");
    tmpDetails.style.display = "none";
    console.log("clear");
}

function addOption(slot, key){
    var attId = document.createAttribute("id");
    attId.value = key;

    var attClass = document.createAttribute("class");
    attClass.value = "my-button";

    var attMouseEnter = document.createAttribute("onmouseenter");
    attMouseEnter.value = "test(\"" + key + "\")";

    var attMouseOut = document.createAttribute("onmouseleave");
    attMouseOut.value = "clear()";

    var newDiv = document.createElement("div");
    newDiv.setAttributeNode(attId);
    newDiv.setAttributeNode(attClass);
    newDiv.setAttributeNode(attMouseOut);
    newDiv.setAttributeNode(attMouseEnter);

    getTitleFromKey(newDiv , key)

    var custom_slot =  document.getElementById(slot);
    custom_slot.appendChild(newDiv);
}

function getTitleFromKey(element , key){
    var ajax_url = "/api/EFT2/item/details/" + cleanKey(key);
    console.log(ajax_url);
    $.ajax({
        dataType: 'json',
        url: ajax_url,
        cache: false,
        success: function(data){
            element.innerHTML  = data.title;
        }
    });
}

function getMainItemSlots (key) {
     var ajax_url = "/api/EFT2/item/slots/" + cleanKey(key);
     console.log(ajax_url);
     $.ajax({
          dataType: 'json',
          url: ajax_url,
          cache: false,
          success: function(data){
            var dataKeys = Object.keys(data);
            for(var i = 0, length = dataKeys.length ; i < length; i++) {
                addItemSlotBox(dataKeys[i]);
                var options = data[dataKeys[i]];
                 for(var j = 0, length2 = options.length ; j < length2; j++) {
                    addOption(dataKeys[i], options[j]);
                 }
            }
          }
     });
};

function cleanKey(key){
    return key.replace("/", "%2F")
}

$( document ).ready(function() {
    console.log( "ready!" );
    setMainItem(key);
    getMainItemSlots(key);

});

