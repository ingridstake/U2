/**
 * A tag button that uses the filter function when it's clicked
 * @param t_name is the name of the button (the tag name)
 * @param filterCategory is the filter function
 * @returns a tag button
 */
export const CategoryTagButton = (t_name: string, active: boolean, onTagClick: (tagName: string) => void) => {
    return (
        <div>
            <button 
                style={{"opacity": active ? 1 : .4}}    // if active, opacity is 1, else 0.4
                onClick={() => onTagClick(t_name)}
            >
                    {t_name}
            </button>
        </div>
    );
}
