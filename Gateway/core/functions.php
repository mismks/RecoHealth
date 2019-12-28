<?php
/**
 * Copyright (c) 2019.
 * (5664913) - RecoHealth
 */


$db = new MysqliDb ('localhost', 'root', '', 'RecoHealth');

function output($data) {

    if (!defined("DEBUG")) {
        return;
    }

    print_r($data);

    echo "\r\n<br>";
}

function random($length) {

    return substr(md5(mt_rand()), 0, $length);
}

function verifyParameters($requiredParameters, $passedParameters, &$assignTo) {

    foreach ($requiredParameters as $parameter) {

        if (!isset($passedParameters[$parameter])) {
            throw new Exception("Missing required parameter $parameter");
        }

        $assignTo[$parameter] = $passedParameters[$parameter];
    }
}

function pop($key, $array) {

    $value = $array[$key];

    unset($array[$key]);

    return $value;
}

function forceGet($key, $array, $pop = false) {

    if (!isset($array[$key])) {
        throw new Exception("Required value $key missing");
    }

    $value = $array[$key];

    if ($pop) {
        unset($array[$key]);
    }

    return $value;
}

function distribute($command) {

    $servers = [];

    foreach ($servers as $server) {

        $newCommand = str_replace("$blockchainIp", $server, $command);

        shell_exec("$newCommand &");
    }

    return shell_exec($command);
}