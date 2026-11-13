# Task 5 – Event handling styles

| Style | Implementation | Readability / reuse |
|---|---|---|
| Frame implements ActionListener | `ConverterFrameListener` | Simple for a tiny frame; frame becomes its own handler and may accumulate unrelated event code. |
| Named inner class | `ConverterNamedInner` | Named, reusable handler; has direct access to outer frame fields. |
| Anonymous inner class | `ConverterAnonymous` | Convenient for one-off handler; less convenient to reuse or name. |
| Lambda expression | `Converter` | Concise for a functional interface; shared lambda listener can be registered on button and text field. |

## Changed requirement
Each implementation registers the same conversion action on the Convert button and the amount field, so pressing Enter invokes the same conversion. The result is displayed in a dialog. The change requires one additional registration for the text field and a dialog display in the conversion routine in each version.

## Alternative concept
A method reference `this::convert` can be registered wherever the listener signature matches the method. A shared Swing Action can be attached to a button, menu item and keyboard shortcut. One shared action object avoids creating separate handler objects for each trigger; adding a trigger requires registering the existing action with that component. With separate listener objects, space is O(k) for k triggers; a shared Action can use O(1) handler objects plus O(k) component registrations.
