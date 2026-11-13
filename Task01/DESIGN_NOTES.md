# Task 1 design notes

## 1.1 Deficiencies in the supplied design
1. Interface and payment/database logic are mixed in one listener, making the window difficult to test and maintain.
2. `==` compares String references rather than String contents; action-command comparison should use `.equals()` or distinct listeners/actions.
3. Empty, malformed, negative and non-finite amounts are not validated before parsing/payment.
4. Database access runs on the event-dispatch thread (EDT), so the window can freeze while the database responds.
5. `Thread.sleep(3000)` on the EDT freezes repainting and user interaction for three seconds.
6. The Receipt button is not implemented as a distinct action; it falls into the `else` branch and clears the fields.
7. SQL is built by concatenating user input, exposing the application to SQL injection and malformed SQL.
8. The connection and statement are not closed reliably.
9. Checked SQL/interruption exceptions are not handled.
10. No useful success/error feedback is displayed in the window; only console output is used.
11. No window-closing behaviour is supplied.

## 1.2 Repaired class structure
- `FeeWindow` (JFrame): collects fields, registers handlers, displays status/dialogs.
- `FeeService`: validates the registration number and amount and performs the payment operation.
- A production JDBC implementation can be injected behind the service boundary; the supplied runnable demonstration simulates payment and receipt creation.

Each button has its own listener. Payment validation and payment processing are not embedded in `actionPerformed`.

## 1.3 Design defence (under 100 words)
The redesigned window only handles presentation and user interaction, while FeeService owns validation and payment logic. The service can be tested without opening a window, and the GUI can be changed without rewriting payment rules. Separate button handlers avoid a long action-command conditional. This separation reduces coupling, makes errors easier to isolate and supports later replacement of the simulated payment operation with a JDBC service.

## Alternative concept and complexity
With one listener and an if–else chain, dispatch can require up to k command comparisons: O(k) worst-case time and O(1) listener-object space. With one listener object per button or shared Swing Action objects, dispatch is direct to the registered handler: O(1) handler selection per event, with O(k) listener/action objects. Both approaches still perform the selected action's own work.
