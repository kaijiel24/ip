package duke.exceptions;

/** Used when the entry format does not follow:
 *      T | [1/0] | [description] OR
 *      [E/D] | [1/0] | [description] | [time/date]
 */
public class EntryFormatError extends Exception {
}
