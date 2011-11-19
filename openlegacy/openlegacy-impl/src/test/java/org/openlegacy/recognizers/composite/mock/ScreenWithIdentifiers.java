package org.openlegacy.recognizers.composite.mock;

import org.openlegacy.annotations.screen.Identifier;
import org.openlegacy.annotations.screen.ScreenEntity;
import org.openlegacy.annotations.screen.ScreenIdentifiers;
import org.openlegacy.modules.login.Login;
import org.springframework.stereotype.Component;

@Component
@ScreenEntity(supportTerminalData = true, screenType = Login.LoginScreen.class)
@ScreenIdentifiers(identifiers = { @Identifier(row = 1, column = 36, value = "Sign On") })
public class ScreenWithIdentifiers implements org.openlegacy.terminal.ScreenEntity {

	public String getFocusField() {
		return null;
	}

	public void setFocusField(String focusField) {}

}
