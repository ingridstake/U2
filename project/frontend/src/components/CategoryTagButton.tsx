
export const CategoryTagButton = (t_name: string, filterCategory: ((arg0: string) => void)) => {

    const shoot = (name: string) => {
        alert(name);
    }

    return (
        <>
          <button onClick={() => filterCategory(t_name)}>
              {t_name}
            </button>
        </>
    );
}
