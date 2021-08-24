var url = document.getElementById("blade-img");
document.getElementById("url").innerHTML = url.dataset.parent;
document.getElementById("blade-img").style.backgroundImage = "url(" + url.dataset.parent + ")";
