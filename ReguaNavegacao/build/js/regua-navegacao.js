(function() {
  var ReguaNavegacao, init;

  ReguaNavegacao = (function() {
    function ReguaNavegacao() {
      console.log("Hello World");
    }

    return ReguaNavegacao;

  })();

  init = function() {
    return new ReguaNavegacao();
  };

  document.addEventListener('DOMContentLoaded', init, false);

}).call(this);

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

        var products = localStorage.getItem(products_key);
        var sprites = localStorage.getItem(sprites_key);

        if (products && sprites) {
            exibirDados(JSON.parse(products), sprites);
        }
        else {
            $.when( $.get("https://matchesjson.herokuapp.com/products.json"),
                    $.get("/sprites.svg") ).done(function(products, sprites) {
       
              localStorage.setItem(products_key, JSON.stringify(products[0]));
              localStorage.setItem(sprites_key, sprites[0]);
              
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
