import { CategoryTagButton } from './CategoryTagButton';

/**
 * Generates a collection of buttons with tags.
 * @param tags is a list of strings that represents the tags for a category
 * @returns a collection of buttons that each represents one of theese tags
 */
export function CategoryTagButtons(tags: string[], selectedTags: string[], onTagClick: (tagName: string) => void): JSX.Element[] {
  const res = tags.map((tName: string) => {
    // active if selectedTags is undefined, empty or contains tag name
    const active = !selectedTags || selectedTags.length == 0 || selectedTags.includes(tName)
    return CategoryTagButton(tName, active, onTagClick)
  })
  return res
}
