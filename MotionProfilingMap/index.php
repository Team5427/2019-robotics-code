<!DOCTYPE html>
<html lang="en" dir="ltr">
  <head>
    <meta charset="utf-8">
    <title>Motion Profiling Map</title>
  </head>
  <body>
    <?php
      $host = "localhost";
      $port = 5426;
      $data = 'Motion 5 0 0 10 0 0';
      if ( ($socket = socket_create(AF_INET, SOCK_STREAM, SOL_TCP)) === FALSE )
          echo "socket_create() failed: reason: " .             socket_strerror(socket_last_error());
      else
      {
          echo "Attempting to connect to '$host' on port '$port'...<br>";
          if ( ($result = socket_connect($socket, $host, $port)) === FALSE )
              echo "socket_connect() failed. Reason: ($result) " .     socket_strerror(socket_last_error($socket));
          else {
              echo "Sending data...<br>";
              socket_write ($socket, $data."\r\n", strlen ($data."\r\n"));
              echo "OK<br>";
              echo "Reading response:<br>";
          }
          socket_close($socket);
      }
    ?>
  </body>
</html>