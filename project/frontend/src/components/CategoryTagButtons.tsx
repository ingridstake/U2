import { Button} from 'react-bootstrap';

/**
 * Generates a collection of buttons with tags.
 * @param tags is a list of strings that represents the tags for a category
 * @returns a collection of buttons that each represents one of theese tags
 */
export function CategoryTagButtons(tags: string[]) : JSX.Element[] {
    const res = tags.map((t : string) => {
        return <Button>{t}</Button>
      })
    return res
}