var key = "M4A1";
key = "M4A1";

function setMainItem (key) {
     var ajax_url = "/api/EFT2/item/details/" + cleanKey(key);
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


function addItemSlotBox(itemTitle, key){
    var newDiv = document.createElement("div");

    var attClass = document.createAttribute("class");
    attClass.value = "slot";

    var attId = document.createAttribute("id");
    attId.value = key+"-"+itemTitle;

    newDiv.setAttributeNode(attClass);
    newDiv.setAttributeNode(attId);
    newDiv.appendChild(getSlotTitle(itemTitle));

    document.getElementById("main").appendChild(newDiv);
}

function setTmpItem (key) {
     var ajax_url = "/api/EFT2/item/details/" + cleanKey(key);
     $.ajax({
          dataType: 'json',
          url: ajax_url,
          cache: false,
          success: function(data){
             document.getElementById("tmp-ergonomics").innerHTML  = data.ergonomics;
             document.getElementById("tmp-accuracy").innerHTML  = data.accuracy;
             document.getElementById("tmp-recoil").innerHTML  = data.recoil;
             document.getElementById("tmp-soldBy").innerHTML  = data.soldBy;
             document.getElementById("tmp-slotCount").innerHTML  = data.slotCount;
          }
     });
};

function hide(){
    var tmpDetails = document.getElementById("tmp-details");
     tmpDetails.style.display = "none";
}

function showItemDetails(key){
    var x = event.clientX;     // Get the horizontal coordinate
    var y = event.clientY

    var tmpDetails = document.getElementById("tmp-details");

    var doc = document.documentElement;
    var left = (window.pageXOffset || doc.scrollLeft) - (doc.clientLeft || 0);
    var top = (window.pageYOffset || doc.scrollTop)  - (doc.clientTop || 0);

    tmpDetails.style.top = y + top;
    tmpDetails.style.left = x + left ;
    tmpDetails.style.display = "block";

    setTmpItem (key)
}

function selector(element){
    element.style.border = "thick solid black"
    getMainItemSlots (element.getAttribute("id") )
    console.log(element.getAttribute("id"));
}

function addOption(itemKey , slotType, attachment){
    var attId = document.createAttribute("id");
    attId.value = attachment;

    var attClass = document.createAttribute("class");
    attClass.value = "my-button";

    var attMouseEnter = document.createAttribute("onmouseenter");
    attMouseEnter.value = "showItemDetails(\"" + attachment + "\")";

    var onClick = document.createAttribute("onClick");
    onClick.value = "selector(this)";

    var newDiv = document.createElement("div");
    newDiv.setAttributeNode(attId);
    newDiv.setAttributeNode(attClass);
    newDiv.setAttributeNode(attMouseEnter);
    newDiv.setAttributeNode(onClick);

    getTitleFromKey(newDiv , attachment)

    var id = itemKey + "-" + slotType;
    var custom_slot =  document.getElementById(id);
    custom_slot.appendChild(newDiv);
}

function getTitleFromKey(element , key){
    var ajax_url = "/api/EFT2/item/details/" + cleanKey(key);
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
     $.ajax({
          dataType: 'json',
          url: ajax_url,
          cache: false,
          success: function(data){
            var dataKeys = Object.keys(data);
            for(var i = 0, length = dataKeys.length ; i < length; i++) {
                addItemSlotBox(dataKeys[i], cleanKey(key));
                var options = data[dataKeys[i]];
                 for(var j = 0, length2 = options.length ; j < length2; j++) {
                    console.log(cleanKey(key) + " --- " + dataKeys[i] + " --- " + options[j]);
                    addOption( cleanKey(key) , dataKeys[i] , options[j] );
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

