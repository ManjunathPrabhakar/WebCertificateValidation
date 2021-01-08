import javax.net.ssl.HttpsURLConnection;
import java.net.URL;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.Arrays;

/**
 * @author Manjunath Prabhakar (Manjunath-PC)
 * @created 08/01/2021
 * @project WebCertificateValidation
 */
public class CertificateTest {

    public static void main(String[] args) throws Exception {

        getCertificateDetails("https://www.google.co.in/");

    }

    private static void getCertificateDetails(final String urldata) throws Exception {
        URL url = new URL(urldata);

        HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
        con.connect();

        Certificate[] certs = con.getServerCertificates();

        int i = 1;

        System.out.println("Requested URL : " + url.toString() + " has " + certs.length + " certificate(s)");

        for (Certificate c : certs) {

            System.out.println("\nCertificate " + i + " : \n");

            if (c instanceof X509Certificate) {
                X509Certificate x = (X509Certificate) c;
                System.out.println("Distinguished Name : " + x.getIssuerDN());
                System.out.println("Cert Name : " + x.getIssuerX500Principal().getName());
                System.out.println("Cert Version : " + x.getVersion());
                System.out.println("Signature Algo Name : " + x.getSigAlgName());
                System.out.println("Signature Algo OID : " + x.getSigAlgOID());
                System.out.println("Cert Valid From : " + x.getNotBefore());
                System.out.println("Cert Valid till : " + x.getNotAfter());
                System.out.println("Cert Public Key : " + x.getPublicKey());
                System.out.println("Cert Signature : " + Arrays.toString(x.getSignature()));
            }
            i = i + 1;
        }

        con.disconnect();
    }
}
