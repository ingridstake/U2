import { CategoryTagButton } from './CategoryTagButton';

/**
 * Generates a collection of buttons with tags.
 * @param tags is a list of strings that represents the tags for a category
 * @returns a collection of buttons that each represents one of theese tags
 */
export function CategoryTagButtons(tags: string[], filterCategory: ((arg0: string) => void)) : JSX.Element[] {
    const res = tags.map((t : string) => {
        return CategoryTagButton(t, filterCategory)
      })
    return res
}
