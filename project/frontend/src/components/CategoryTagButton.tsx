
export const CategoryTagButton = (t_name: string, filterCategory: { (tagName: string): void; (arg0: string): void; (arg0: string): void; }) => {
    return (
        <>
          <button onClick={() => filterCategory(t_name)}>
              {t_name}
            </button>
        </>
    );
}
