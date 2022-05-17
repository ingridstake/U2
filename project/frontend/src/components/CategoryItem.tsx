import Carousel from 'react-multi-carousel';
/*import 'react-multi-carousel/lib/styles.css'*/
import { ListGroupItem } from 'react-bootstrap';
import '../styles/showEventCat.css';
import { EventCards} from './EventCards';
import { CategoryTagButtons } from './CategoryTagButtons';
import { category, event } from './Models'


export const CategoryItem = ( c : category ) => {

  var eventsToShow = [] as JSX.Element[];

  const filtered = [] as event[];

  const filterCategory = (tag_name : string) => {
    //alert(tag_name)
    c.events.forEach( (e : event) => {
      if(e.e_tags === tag_name) {
        filtered.push(e)
      }
    })
  }


  if(filtered.length === 0) {
    eventsToShow = EventCards(c.events);
  }
  else {
    eventsToShow = EventCards(filtered);
  }


  const res = (
    <ListGroupItem>
      <div className="category-title">
        <h1 className="cat-title">{c.category}</h1>
        <div className='cat-tags'>{CategoryTagButtons(c.tags, filterCategory)}</div>
      </div>
      
      <Carousel 
      responsive={responsive}
      showDots={true}
      /*infinite={true}*/
      shouldResetAutoplay={false}
      customTransition="1000ms cubic-bezier(0.645, 0.045, 0.355, 1) 0s"
      >
        {eventsToShow}

      </Carousel>
    </ListGroupItem>
  )

  return ( 
      res
  );

}

/**
     * Responsive item settings for different platform sizes.
     * Used in Carousel.
     */
 const responsive = {
    desktopBig: {
    breakpoint: { max: 3000, min: 1550 },
    items: 4,
    slidesToSlide: 4,
    },
    desktopSmall: {
    breakpoint: { max: 1550, min: 1048 },
    items: 3,
    slidesToSlide: 3,
    },
    tablet: {
    breakpoint: { max: 1048, min: 576 },
    items: 2,
    slidesToSlide: 2,
    },
    mobile: {
    breakpoint: { max: 576, min: 0 },
    items: 1,
    slidesToSlide: 1,
    }
};


  