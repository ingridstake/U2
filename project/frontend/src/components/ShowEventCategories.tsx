/*import 'react-multi-carousel/lib/styles.css'*/
import { ListGroup } from 'react-bootstrap';
import { useEffect, useState } from 'react';
import axios from 'axios';
import '../styles/showEventCat.css';
import { category } from './Models';
import { CategoryItem } from './CategoryItem';

/**
 * Retrieves data from backend using axios.
 * Creates a list group with different categories as carousels.
 * Adds all events as cards to their respective category carousel.
 * @returns a list of categorized carousels with events
 */
export default function DataCat() {
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



  const categoryList = 
      <ListGroup>
        {appState.cat.map(c => (
          <CategoryItem category={c.category} events={c.events} tags={c.tags}/>
        ))}
      </ListGroup >
  
  return (
    categoryList
  );
}