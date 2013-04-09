import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.example.LoginCompleteActivity;
import com.example.MyActivity;
import com.example.R;
import com.xtremelabs.robolectric.RobolectricTestRunner;
import com.xtremelabs.robolectric.shadows.ShadowActivity;
import com.xtremelabs.robolectric.shadows.ShadowIntent;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.xtremelabs.robolectric.Robolectric.shadowOf;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(RobolectricTestRunner.class)
public class MyActivityTest {
  private MyActivity activity;
  private Button signIn;
  private EditText userName;
  private EditText password;
  private EditText userNameError;
  private EditText passwordError;

  @Before
  public void setup() throws Exception{
    activity = new MyActivity();
    activity.onCreate(null);
    signIn = (Button) activity.findViewById(R.id.sign_in_button);
    userName = (EditText) activity.findViewById(R.id.username);
    password = (EditText) activity.findViewById(R.id.password);
    userNameError = (EditText) activity.findViewById(R.id.username_error);
    passwordError = (EditText) activity.findViewById(R.id.password_error);
  }

  @Test
  public void shouldHaveHappySmiles() throws Exception {
    String appName = activity.getResources().getString(R.string.app_name);
    assertThat(appName, equalTo("MyProject"));
  }
  @Test
  public void shouldHaveAButtonThatSaysSignIn() throws Exception {
    assertThat((String) signIn.getText(), equalTo("Sign In"));
  }

  @Test
  public void shouldShowErrorIfUsernameFieldIsEmpty() throws Exception {
    userName.setText("");
    password.setText("da");
    signIn.performClick();
    assertThat(userNameError.getVisibility(), equalTo(View.VISIBLE));
    assertThat(passwordError.getVisibility(), equalTo(View.INVISIBLE));
    assertThat(userNameError.getText().toString(), equalTo("Enter valid username"));
  }

  @Test
  public void shouldHideUserNameErrorFieldOnTouchEventOfUserName() throws Exception {
    userName.setText("");
    password.setText("da");
    signIn.performClick();
    assertThat(userNameError.getVisibility(), equalTo(View.VISIBLE));
    assertThat(passwordError.getVisibility(), equalTo(View.INVISIBLE));
    assertThat(userNameError.getText().toString(), equalTo("Enter valid username"));
    userName.requestFocus();
    assertThat(userNameError.getVisibility(), equalTo(View.GONE));

  }

  @Test
  public void shouldShowErrorIfPasswordFieldIsEmpty() throws Exception {
    userName.setText("adfsa");
    password.setText("");
    signIn.performClick();
    assertThat(userNameError.getVisibility(), equalTo(View.INVISIBLE));
    assertThat(passwordError.getVisibility(), equalTo(View.VISIBLE));
    assertThat(passwordError.getText().toString(), equalTo("Enter valid password"));
  }

  @Test
  public void shouldStartLoginCompleteFulActivityIfLoginCredentialsAreValid() throws Exception {
    userName.setText("adfsa");
    password.setText("cvx");
    signIn.performClick();
    ShadowActivity shadowActivity = shadowOf(activity);
    Intent intent = shadowActivity.getNextStartedActivity();
    ShadowIntent shadowIntent = shadowOf(intent);
    assertThat(shadowIntent.getComponent().getClassName(), equalTo(LoginCompleteActivity.class.getName()));
  }
}