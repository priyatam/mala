(function() {

  $(function() {
    window.socket = new WebSocket(window.location.href.replace("http://", "ws://"));
    socket.onopen = function() {
      return console.log("socket opened");
    };
    socket.onmessage = function(msg) {
      return $("#messages").append("<p>" + msg.data + "</p>");
    };
    return $("form").on("submit", function(e) {
      e.preventDefault();
      socket.send($("#message").val());
      return $("#message").val("");
    });
  });

}).call(this);
