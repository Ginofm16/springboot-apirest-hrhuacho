package com.hregional.springboot.backend.apirest.auth;

public class JwtConfig {
	
	public static final String LLAVE_SECRETA = "alguna.clave.secreta.123";
	
	public static final String RSA_PRIVADA = "-----BEGIN RSA PRIVATE KEY-----\r\n" + 
			"MIIEowIBAAKCAQEAyqrWfkCKn5BDeLK9y62UeOYlan8rCjSaPTvUfKEniAbvRUT3\r\n" + 
			"TEdmH67KvCnVP7DerHemOQhJ66x37VEizigBnTgRrFHIsW5ERtE3rBgq8hz7ADrI\r\n" + 
			"xL4KUIGK0mcMK0qm2ifXTVBHshdyCY7XHvYNsNkmv6hVBBJTnWCoFnWEHrt4ywke\r\n" + 
			"XE+Jb49viUV9Up1LTh8a0wFa/JfdXXAJ0+rvB63EbdduW5nPXAqoN3p43eD+ksIs\r\n" + 
			"7nSPTolOvaoNZ2k9jdeGnv1YdhULtIy+SYZrojWxrQqwbpnkIsTLnbdy48GZ7Q89\r\n" + 
			"xt8ebl6hagnZLLvlEhdzgFVgVxcfYDCYLrCUYwIDAQABAoIBADZPFW97qCoeJJaB\r\n" + 
			"f27HMxQ6yCgDr+rqMbLbB5PmM4u2IAQ1+PcN4wX2dwl5RFXu9ZzN61M5L2XiL7j+\r\n" + 
			"lw8blWMmFUZSHC+/1rgLdJm/WDLNJU3ocsiWDHmKXUMIrjAjrxaiuoVTUAMfWUXK\r\n" + 
			"a0c2tHCBoGqXl0CiEJoJvsFV+HOrWuXnPFcYWVvpFHeO2qRAyd1TcNVp+mUJ5TR/\r\n" + 
			"6yHRs8GofoqVi5jCNjyFb3WMKa4lRyw5PA4+S4+ky451k+1BQC1YgprpvA/1si0d\r\n" + 
			"K50QQFAjnwtruGlP4qJiug5lB5YpxA7xKjSV1KpbviHErDz7gGkuHINtp0c71g+4\r\n" + 
			"TcaCGtECgYEA8cK14GtguZIdgheOmyyHwjlxzrlIgnzoG6lAL1rAM+y41ZIzAEW+\r\n" + 
			"jBwKvIlbQZ4zauLTVXlfuAvAitf0RI1SFu0/jDsbMpQ4fKbADYgNigR2RvKxRqk8\r\n" + 
			"A173q4V/pG91xj79oalPsvFq5LaHkXbUee5UgvXpHIj41jMXwa7UYx8CgYEA1pqs\r\n" + 
			"8d+413D1BuLe0j5oIGqw4YBj3XCrB7nEoYKwJSWuhgi991ZY/R/RkPfbiCVs8+b4\r\n" + 
			"MimgSV61XEQm7NDNZmDLhddqSEJVzsW9ou70RP76DqUVv+Bb3f3EuP1/Sr+6BnSl\r\n" + 
			"oul2NnRYg8zNfzl6p4HkRFXqFSVjgeMKeO7SSj0CgYABTz+y9oHwn2T/hmckWgpm\r\n" + 
			"N+9w4joNUuXi+Hi7iO9PmLGrnn4eO0J7+c7pFIllAples9nXNbAaDB4/4kmVcXqH\r\n" + 
			"W0Oj29Ia5QilTDXwHNON8tY9LiVdr57+ma4XxMJbPqtRppezH3qqCnq8wGxdklo2\r\n" + 
			"St80Umav2+IVLvOq/epymwKBgGQwKfVxwNWzwnDIkcYNpIcdyB4+1mCXLQGaVJYi\r\n" + 
			"cSGmiefBRQ6nnTvvEuNfE3poHolvlwe0L0SRlpHQr2t3v6zAG8QMYW4CqhSydGoQ\r\n" + 
			"rLFZaFxV7eV6VOk6hBDpJ/g5shs5ceqwm6OohXpirr1qlDyHg43znijpH5+P41+5\r\n" + 
			"/yrxAoGBAN7yBvlRVpGuvOhIgWpR62spDz8XHIE3/ocPPHLHXkSAj1oNxa9Ed+MN\r\n" + 
			"NXLy5YOpM5rGPajE7kqtEQOHVGCKNsCNTNt1fO2NX6BUjaWdqvjO7qcqQ7uNrite\r\n" + 
			"c9S7az/OR8L+xAHcIWatknkgo8T0y8c3aqr5KbgFC4EH+p4aGCZU\r\n" + 
			"-----END RSA PRIVATE KEY-----";
	
	public static final String RSA_PUBLICA = "-----BEGIN PUBLIC KEY-----\r\n" + 
			"MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAyqrWfkCKn5BDeLK9y62U\r\n" + 
			"eOYlan8rCjSaPTvUfKEniAbvRUT3TEdmH67KvCnVP7DerHemOQhJ66x37VEizigB\r\n" + 
			"nTgRrFHIsW5ERtE3rBgq8hz7ADrIxL4KUIGK0mcMK0qm2ifXTVBHshdyCY7XHvYN\r\n" + 
			"sNkmv6hVBBJTnWCoFnWEHrt4ywkeXE+Jb49viUV9Up1LTh8a0wFa/JfdXXAJ0+rv\r\n" + 
			"B63EbdduW5nPXAqoN3p43eD+ksIs7nSPTolOvaoNZ2k9jdeGnv1YdhULtIy+SYZr\r\n" + 
			"ojWxrQqwbpnkIsTLnbdy48GZ7Q89xt8ebl6hagnZLLvlEhdzgFVgVxcfYDCYLrCU\r\n" + 
			"YwIDAQAB\r\n" + 
			"-----END PUBLIC KEY-----";
}
