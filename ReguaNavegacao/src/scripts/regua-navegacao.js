(function() {
    var ReguaNavegacao, init;

    ReguaNavegacao = (function() {
      function ReguaNavegacao() {
        console.log("Hello World Js");

        obterDados();
      }

      function obterDados() {
        var products_key = "products";
        var sprites_key = "sprites";
        var update_key = "update_time";

        var products = localStorage.getItem(products_key);
        var sprites = localStorage.getItem(sprites_key);
        var update_time = localStorage.getItem(update_key);
        var time_now = new Date().getTime();

        var expired = ((time_now - update_time) / 1000) > 5; // 5 segundos de cache

        if (!expired && products && sprites) {
            console.log("Cached");
            exibirDados(JSON.parse(products), sprites);
        }
        else {
            console.log("Requisitando...");

            $.when( $.get("https://matchesjson.herokuapp.com/products.json"),
                    $.get("/sprites.svg") ).done(function(products, sprites) {
       
              localStorage.setItem(products_key, JSON.stringify(products[0]));
              localStorage.setItem(sprites_key, sprites[0]);
              localStorage.setItem(update_key, new Date().getTime());
              
              exibirDados(products[0], sprites[0]);
            });
        }
      }

      function exibirDados(products, sprites) {
        var reguaElement = $("#regua-navegacao");
        console.log("Exibindo dados");

        for (item in products) {
          var itemData = products[item];
          var spritesElements = $("<div>").html(sprites);
          var itemIcon = spritesElements.find("#icone-logo-"+item);
          
          var itemElement = $("<a>");
          itemElement.attr("href", itemData.link)
                     .addClass("regua-navegacao-item")
                     .css("background-color", itemData.color);

          itemElement.html(itemIcon);
          reguaElement.append(itemElement);
        }
      }

      return ReguaNavegacao;

    })();

    init = function() {
      new ReguaNavegacao();
    };

    document.addEventListener('DOMContentLoaded', init, false);
}).call(this);
