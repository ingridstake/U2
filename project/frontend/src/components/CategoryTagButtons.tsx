import { CategoryTagButton } from './CategoryTagButton';

/**
 * Generates tag buttons.
 * @param tags is a list of strings that represents the tags for a category
 * @param selectedTags is the string array of selected tags 
 * @param onTagClick is the filtering function
 * @returns tag buttons
 */
export function CategoryTagButtons(tags: string[], selectedTags: string[], onTagClick: (tagName: string) => void): JSX.Element[] {
  const res = tags.map((tName: string) => {
    // active if selectedTags is undefined, empty or contains tag name
    const active = !selectedTags || selectedTags.length === 0 || selectedTags.includes(tName)
    return CategoryTagButton(tName, active, onTagClick)
  })
  return res
}
