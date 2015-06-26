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
        $.get("https://matchesjson.herokuapp.com/products.json").done(function(resp) {
          exibirDados(resp);
        });
      }

      function exibirDados(resp) {
        var reguaElement = $("#regua-navegacao");
        console.log("Exibindo dados");

        for (item in resp) {
          var itemData = resp[item];
          var itemIcon = $("#icone-logo-"+item);
          
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
