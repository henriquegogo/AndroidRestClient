class ReguaNavegacao
  constructor: () ->
    console.log("Hello World")

init = ->
  new ReguaNavegacao()

document.addEventListener('DOMContentLoaded', init, false);
