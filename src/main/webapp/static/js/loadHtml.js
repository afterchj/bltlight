// function leftBar(id) {
//     $(".menu-list a").css("color", "#fff");
//     $(".menu-list a").css("background-color", "#424f63");
//     $("#" + id).css("color", "#65cea7");
//     $("#" + id).css("background-color", "#2a323f");
// }


// (function () {
//     var storage = window.localStorage;
//
//     // //写入a字段
//     storage["a"] = 1;
//     console.log(storage.a);
//     //写入b字段
//     storage.b = 2;
//     console.log(storage["b"]);
//     //写入c字段
//     storage.setItem("c", 3);
//     var c = storage.getItem('c');
//     console.log("c=" + c);
//     /*分割线*/
//     storage.a = 4;
//     console.log(storage.a);
//
//     $('#nav1-1').bind("click", function () {
//         alert("id=" + this.id);
//         storage.setItem('key', this.id);
//     });
//     $('#nav1-2').bind("click", function () {
//         storage.setItem('key', this.id);
//     });
//     $('#nav1-3').bind("click", function () {
//         storage.setItem('key', this.id);
//     });
//     $('#nav1-4').bind("click", function () {
//         storage.setItem('key', this.id);
//     });
//     $('#nav1-5').bind("click", function () {
//         storage.setItem('key', this.id);
//     });
//
//     var id = storage.getItem('key');
//     console.log("id=" + id);
//     if (id != undefined && id != "") {
//         $(".menu-list a").css("color", "#fff");
//         $(".menu-list a").css("background-color", "#424f63");
//         $("#" + id[0]).css("color", "#65cea7");
//         $("#" + id[0]).css("background-color", "#2a323f");
//     }
// })(jQuery);