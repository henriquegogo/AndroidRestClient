(function() {
    var ReguaNavegacao, init;

    ReguaNavegacao = (function() {
      function ReguaNavegacao() {
        console.log("Hello World Js");
      }

      return ReguaNavegacao;

    })();

    init = function() {
      new ReguaNavegacao();
    };

    document.addEventListener('DOMContentLoaded', init, false);
}).call(this);
