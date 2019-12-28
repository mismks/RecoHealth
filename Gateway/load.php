<?php

/**
 * Copyright (c) 2019.
 * (5664913) - RecoHealth
 *
 */
spl_autoload_register(function ($class_name) {

    include $class_name . '.php';
});

foreach (scandir(__DIR__ . '/core') as $filename) {
    $path = __DIR__ . '/core/' . $filename;
    //    echo $path;
    //    echo "</br>";
    if (is_file($path) && $filename !== "test.php") {
        require_once($path);
    }
}
