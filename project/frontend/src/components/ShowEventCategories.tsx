/*import 'react-multi-carousel/lib/styles.css'*/
import { ListGroup } from 'react-bootstrap';
import { useEffect, useState } from 'react';
import axios from 'axios';
import '../styles/showEventCat.css';
import { category } from './Models';
import { CategoryItem } from './CategoryItem';

/**
 * Retrieves data from backend using axios.
 * Creates a ListGroup with all categories.
 * @returns a ListGroup of CategoryItems
 */
export default function ShowEventCategories() {
  const [appState, setAppState] = useState({
    loading: false,
    cat: [] as category[]
  });

  useEffect(() => {
    setAppState({ loading: true, cat: [] });
    const apiUrl = 'http://127.0.0.1:8080/vowel_events';
    axios.get(apiUrl).then(res => {
      const allCategories = res.data
      setAppState({ loading: false, cat: allCategories });
    })
  }, [])

  const onClickFilterTag = (cat: category, tagName: string) => {
    let newStateCat = appState.cat
    const i = newStateCat.findIndex(c => c.category === cat.category)     // find index for right category
    let catTagsNew = cat.selectedTags ? cat.selectedTags : [] as string[] // if selected tags in category isn't already defined, create new array
    if (catTagsNew.includes(tagName)) {                                   // check if tag already exists, then remove it
      catTagsNew = catTagsNew.filter(t => t !== tagName);
    }
    else {
      catTagsNew = [...catTagsNew, tagName]                               // else; add the tag to its category
    }
    newStateCat[i].selectedTags = catTagsNew                              // change selected tags
    setAppState({
      loading: appState.loading,
      cat: newStateCat
    })
  }

  const categoryList =
    <ListGroup>
      {appState.cat.map(c => (
        CategoryItem(c, onClickFilterTag)
      ))}
    </ListGroup >

  return (
    categoryList
  );
}
