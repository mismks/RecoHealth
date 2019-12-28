<?php

ini_set('display_errors', 0);
ini_set('display_startup_errors', 0);
//error_reporting(E_ALL);

require_once("../../api/load.php");

$param = empty($_GET) ? $_POST : $_GET;

$class = pop("class", $param) . 'Api';
$action = pop("action", $param);

output("--------------------------------");

output("Calling $class::$action");

try {

    if (method_exists($class, $action) == false) {
        throw new Exception("Invalid call");
    }

    $result = call_user_func("$class::$action", $param);

    output("Reply to client with:");
    output($result);

    echo json_encode(["result" => $result]);

} catch (Exception $e) {

    output("Reply to client with:");
    output($e->getMessage());

    echo json_encode(["error" => $e->getMessage()]);
}