import Carousel from 'react-multi-carousel';
/*import 'react-multi-carousel/lib/styles.css'*/
import { Button, ListGroup, ListGroupItem } from 'react-bootstrap';
import { useEffect, useState } from 'react';
import axios from 'axios';
import '../styles/showEventCat.css';
import { EventCards} from './EventCards';
import { category } from './Models';
import { CategoryTagButtons } from './CategoryTagButtons';
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
        CategoryItem(c)
      ))}
    </ListGroup>
  
  return (
    categoryList
  );
}