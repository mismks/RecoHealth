<?php

// https://www.php.net/manual/en/book.openssl.php
class RSA {

    public static function generate() {

        // Create the keypair
        $res = openssl_pkey_new();

        // Get private key
        openssl_pkey_export($res, $privatekey);

        // Get public key
        $publickey = openssl_pkey_get_details($res);
        $publickey = $publickey["key"];

        return ["public"  => $publickey,
                "private" => $privatekey];
    }

    public static function encrypt($data, $public) {

        openssl_public_encrypt($data, $crypttext, $public);

        return base64_encode(base64_encode($crypttext));
    }

    public static function decrypt($data, $private) {

        $data = base64_decode(base64_decode($data));

        openssl_private_decrypt($data, $decrypted, $private);

        return $decrypted;
    }

}