// This file is part of MongoFX.
//
// MongoFX is free software: you can redistribute it and/or modify
// it under the terms of the GNU General Public License as published by
// the Free Software Foundation, either version 3 of the License, or
//  (at your option) any later version.
//
// MongoFX is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU General Public License for more details.
//
// You should have received a copy of the GNU General Public License
// along with MongoFX.  If not, see <http://www.gnu.org/licenses/>.

//
// Copyright (c) Andrey Dubravin, 2015
//
package mongofx.ui.result.tree;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.scene.Node;
import javafx.scene.control.Tooltip;
import javafx.scene.control.TreeTableCell;

public class ResultValueTreeTableCell extends TreeTableCell<DocumentTreeValue, Object> {
	private static final Pattern NEW_LINE = Pattern.compile("[\n\r]");
	
	@Override
	protected void updateItem(Object item, boolean empty) {
		if (item == getItem())
			return;

		super.updateItem(item, empty);

		if (item == null) {
			super.setText(null);
			super.setGraphic(null);
		} else if (item instanceof Node) {
			super.setText(null);
			super.setGraphic((Node) item);
		} else {
			String displayValue = item.toString();
			super.setText(limitTo(displayValue, 200));
			super.setTooltip(new Tooltip(displayValue));
			super.setGraphic(null);
		}
	}

	private String limitTo(String displayValue, int limit) {
		if (displayValue == null || displayValue.isEmpty()) {
			return displayValue;
		}
		Matcher newLineMatcher = NEW_LINE.matcher(displayValue);
		if (newLineMatcher.find()) {
			displayValue = newLineMatcher.replaceAll("");
		}
		if (displayValue.length() > limit) {
			return displayValue.substring(0, limit);
		}
		return displayValue;
	}
}
