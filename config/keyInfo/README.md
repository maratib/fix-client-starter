Create cert.p12 by
```bash
openssl pkcs12 -export -out cert.p12 -in JAMIFIXUAT.pem  -inkey JAMIFIXUAT.key.pem
```

Then import cert.p12 and comodorsacertificationauthority.crt using KeyStore Explorer

and save it as quickfixj.keystore

and put this file to the root of the project.



PASSWORD : 123456


