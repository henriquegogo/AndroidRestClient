(function() {
    var ReguaNavegacao, init;

    ReguaNavegacao = (function() {
      function ReguaNavegacao() {
        console.log("Hello World Js");

        obterDados();
      }

      function obterDados() {
        $.when( $.get("https://matchesjson.herokuapp.com/products.json"),
                $.get("/sprites.svg") ).done(function(products, sprites) {

          exibirDados(products[0], sprites[0]);
        });
      }

      function exibirDados(products, sprites) {
        var reguaElement = $("#regua-navegacao");
        console.log("Exibindo dados");

        for (item in products) {
          var itemData = products[item];
          var spritesElements = $("<div>").html(sprites);
          var itemIcon = spritesElements.find("#icone-logo-"+item);
          console.log(itemIcon);
          
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
