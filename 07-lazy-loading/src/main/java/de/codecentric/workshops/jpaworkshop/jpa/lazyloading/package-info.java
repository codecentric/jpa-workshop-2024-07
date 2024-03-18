@GenericGenerator(name = "myGenerator", type = SequenceStyleGenerator.class, parameters = {
	@Parameter(name = "sequence_name", value = "workshop_sequence")
})
package de.codecentric.workshops.jpaworkshop.jpa.lazyloading;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.id.enhanced.SequenceStyleGenerator;