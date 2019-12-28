<?php


// https://stackoverflow.com/questions/3422759/php-aes-encrypt-decrypt
class AES {

    static function encrypt($plaintext, $password) {

        $method = "AES-256-CBC";
        $key = hash('sha256', $password, true);
        $iv = openssl_random_pseudo_bytes(16);

        $ciphertext = openssl_encrypt($plaintext, $method, $key, OPENSSL_RAW_DATA, $iv);
        $hash = hash_hmac('sha256', $ciphertext . $iv, $key, true);

        return base64_encode(base64_encode($iv . $hash . $ciphertext));
    }

    static function decrypt($cipher, $password) {

        $cipher = base64_decode(base64_decode($cipher));

        $method = "AES-256-CBC";
        $iv = substr($cipher, 0, 16);
        $hash = substr($cipher, 16, 32);
        $ciphertext = substr($cipher, 48);
        $key = hash('sha256', $password, true);

        if (!hash_equals(hash_hmac('sha256', $ciphertext . $iv, $key, true), $hash)) {
            return null;
        }

        return openssl_decrypt($ciphertext, $method, $key, OPENSSL_RAW_DATA, $iv);
    }
}