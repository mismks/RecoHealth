<?php

/**
 * Copyright (c) 2019.
 * (5664913) - RecoHealth
 *
 */

trait IdentityDB {

    public static function add($username, $password) {

        global $db;

        if (empty($username) || empty($password)) {
            throw new Exception("Username and password required");
        }

        output("Create a random salt");

        $salt = random(12);

        output("Salt: $salt");

        output("Append the salt to the password and hash it using sha256");

        $hashedPassword = hash('sha256', $password . '' . $salt);

        output("Hashed: $hashedPassword");

        output("Generate public/private keys (RSA) using openssl");

        $keys = RSA::generate();

        output("Public: " . $keys["public"]);
        output("Private: " . $keys["private"]);

        output("Add identity to database");

        $id = $db->insert(self::$dbName, ["username" => $username, "password" => $hashedPassword, "salt" => $salt, "publicKey" => $keys["public"], "privateKey" => AES::encrypt($keys["private"], $password)]);

        if ($db->getLastError()) {
            throw new Exception($db->getLastError());
        }

        return $id;
    }

    public static function login($username, $password) {

        global $db;

        output("Login with username $username and password $password");

        $db->where("username", $username);

        $result = $db->getOne(self::$dbName);

        output($result);

        if (!$result) {
            throw new Exception("Invalid username or password");
        }

        output("Found record");

        output("Verify password by appending salt then hashing it");

        $hashedPassword = hash('sha256', $password . '' . $result["salt"]);

        //output($password . '' . $result["salt"]);

        //output($result["salt"]);

        if ($hashedPassword !== $result["password"]) {
            throw new Exception("Invalid username or password");
        }

        output("Password verified, user ok");

        return $result["id"];
    }

    public static function getIdentity($id) {

        global $db;

        $db->where("id", $id);

        $result = $db->getOne(self::$dbName);

        //output($result);

        if (!$result) {
            throw new Exception("Identity doesn't exist");
        }

        return $result;
    }

    public static function remove($id) {

        global $db;

        $db->where("id", $id);

        $db->delete(self::$dbName);
    }


}