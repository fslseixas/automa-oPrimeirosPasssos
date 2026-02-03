package driverfactory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class DriverFactory {

        // 1) Guarda a instância do navegador
        // "static" = uma instância compartilhada por todo o projeto
    private static WebDriver driver;

        // 2) Método público para qualquer classe pegar o driver

    public static WebDriver getDriver() {


            // 3) Se o driver ainda não foi criado, cria
        if (driver == null) {
            iniciarDriver();

        }
            // 4) Retorna o driver pronto
        return driver;
    }
            // 5) Método privado: só a própria classe cria o driver
        private static void iniciarDriver () {

            // 6) Configura o chromedriver automaticamente (baixa a versão correta)
            WebDriverManager.chromedriver().setup();

            // 7) Opções do Chrome (configurações do navegador)
            ChromeOptions options = new ChromeOptions();

            // 8) Se quiser rodar sem abrir janela (CI/CD), descomente:
            //options.addArguments("--headless=new");

            // 9) Inicia o navegador
            driver = new ChromeDriver(options);

            // 10) Maximiza a janela
            driver.manage().window().maximize();

            // 11) Espera implícita: tenta localizar elementos por até 5 segundos
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

            // 12) Timeout de carregamento total da página (até 30s)
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        }

            // 13) Fecha o driver com segurança
    public static void quitDriver() {
        if (driver != null) {
            driver.quit();   // 14) Fecha navegador e encerra sessão
            driver = null;   // 15) Limpa para permitir iniciar novamente
        }
    }
}






