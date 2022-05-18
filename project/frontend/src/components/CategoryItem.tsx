import Carousel from 'react-multi-carousel';
/*import 'react-multi-carousel/lib/styles.css'*/
import { ListGroupItem } from 'react-bootstrap';
import '../styles/showEventCat.css';
import { EventCards} from './EventCards';
import { CategoryTagButtons } from './CategoryTagButtons';
import { category, event } from './Models'
import { Component, Key, useEffect, useState } from 'react';

type eventsToShow = {
  events : JSX.Element[];
}

export class CategoryItem extends Component <category,eventsToShow> implements JSX.Element {
  type: any;
  key: Key | null = null;
  state: eventsToShow = {events: []}
  allEvents: category = {events: [], category: '', tags: []}

  constructor(props: category) {
    super(props)
    console.log(props)
    this.allEvents = {events: this.props.events, category: this.props.category, tags: this.props.tags}
    this.state = {events: EventCards(this.props.events)}
    this.updateEventsToShow = this.updateEventsToShow.bind(this);
  }

  updateEventsToShow(tagName: string){
    let filteredEvents = this.allEvents.events?.filter((e: event) => e.e_tags.includes(tagName));
    this.state.events = EventCards(filteredEvents);
    this.render();
  }
  
  //const [show, setShow] = useState([] as event[]);
  //var eventsToShow = [] as JSX.Element[];
  //const [eventsToShowx, setEventsToShowx] = useState<JSX.Element[]>([]);
  //const [allEvents, setAllEvents] = useState<event[]>([]);

/*

  const filtered = [] as event[];


  const FilterCategory = (tag_name : string) => {
    //alert(tag_name)
    c.events.forEach( (e : event) => {
      if(e.e_tags === tag_name) {
        filtered.push(e)
      }
    })
    setEventsToShowx(EventCards(filtered))
    
  }


  if(filtered.length === 0) {
    setEventsToShowx(EventCards(allEvents))
  }
  else {
    setEventsToShowx(EventCards(filtered))
  }
  */

  render() {
    return (
      <ListGroupItem>
      <div className="category-title">
        <h1 className="cat-title">{this.allEvents.category}</h1>
        <div className='cat-tags'>{CategoryTagButtons(this.allEvents.tags, this.updateEventsToShow)}</div>
      </div>
      
      <Carousel 
      responsive={responsive}
      showDots={true}
      /*infinite={true}*/
      shouldResetAutoplay={false}
      customTransition="1000ms cubic-bezier(0.645, 0.045, 0.355, 1) 0s"
      >
        {this.state.events}

      </Carousel>
      </ListGroupItem>
    )
  };

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


  