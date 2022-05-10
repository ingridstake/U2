import Carousel from 'react-multi-carousel';
import { ListGroup, ListGroupItem } from 'react-bootstrap';
import { useEffect, useState } from 'react';
import axios from 'axios';
import '../styles/showEventCat.css';
import { EventCards} from './EventCards';
import { category } from './Models';

/**
 * Responsive item settings for different platform sizes.
 * Used in Carousel.
 */
const responsive = {
  desktopBig: {
    breakpoint: { max: 3000, min: 1550 },
    items: 4,
  },
  desktopSmall: {
    breakpoint: { max: 1550, min: 768 },
    items: 3,
  },
  tablet: {
    breakpoint: { max: 767, min: 576 },
    items: 2,
  },
  mobile: {
    breakpoint: { max: 575, min: 0 },
    items: 1,
  }
};

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
  }, [setAppState])

  const categoryList = 
      <ListGroup>
      {appState.cat.map(c => (
        <ListGroupItem><h1 className="cat-title">{c.category}</h1>
          <Carousel
            //ssr
            //partialVisbile
            /*deviceType={deviceType}*/
            //itemClass="image-item"
            responsive={responsive}
          >
            {EventCards(c.events)}
          </Carousel>
        </ListGroupItem>
      ))}
    </ListGroup>
  
  return (
    categoryList
  );
}