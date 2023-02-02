function toggleEditing() {
    var offerName = document.getElementById("OfferName");
    var promoImage = document.getElementById("PromoImage");
    var link = document.getElementById("Link");
    var targets = document.getElementById("Targets");
    var enablebutton = document.getElementById("enableEditing");
    var submeter = document.getElementById("submeter");
    if(offerName.hasAttribute("readonly")){
        offerName.removeAttribute("readonly");
        promoImage.removeAttribute("readonly");
        link.removeAttribute("readonly");
        targets.removeAttribute("readonly");
        enablebutton.innerText = "Cancel";
        submeter.hidden = false;
    }else{
        offerName.setAttribute("readonly", true);
        promoImage.setAttribute("readonly", true);
        link.setAttribute("readonly", true);
        targets.setAttribute("readonly", true);
        enablebutton.innerText = "Enable Editing"
        submeter.hidden=true;
    }
}