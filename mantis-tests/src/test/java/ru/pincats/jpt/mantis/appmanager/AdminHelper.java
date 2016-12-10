package ru.pincats.jpt.mantis.appmanager;

import org.openqa.selenium.By;
import ru.pincats.jpt.mantis.model.MantisUser;

/**
 * Created by PinCatS on 11.12.2016.
 */
public class AdminHelper extends HelperBase {

    public AdminHelper(ApplicationManager app) {
        super(app);
    }

    public void login() {
        app.goTo().loginPage();
        type(By.name("username"), app.properties().getProperty("web.adminLogin"));
        type(By.name("password"), app.properties().getProperty("web.adminPassword"));
        click(By.cssSelector("input[value='Войти']"));
    }

    public void logout() {
        click(By.linkText("выход"));
    }

    public void passwordResetFor(MantisUser user) {
        selectById(user.getId());
        submitPasswordReset();
    }

    private void submitPasswordReset() {
        click(By.cssSelector("input[value='Сбросить пароль']"));
    }

    private void selectById(int id) {
        click(By.cssSelector(String.format("a[href='manage_user_edit_page.php?user_id=%d']", id)));
    }
}
