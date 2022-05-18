
/**
 * A tag button that uses the filter function when it's clicked.S
 * @param t_name is the name of the button (the tag name)
 * @param filterCategory is the filter function
 * @returns a tag button
 */
export const CategoryTagButton = (t_name: string, filterCategory: { (tagName: string): void; (arg0: string): void; (arg0: string): void; }) => {
    return (
        <>
          <button onClick={() => filterCategory(t_name)}>
              {t_name}
            </button>
        </>
    );

}
