package net.revature.app;

import java.time.LocalDate;


public class StoryPitch {
	// Creating a object for the story pitch
		int id;
		String tenativeTitle;
		LocalDate expCompletionDate;
		String lengthType;
		String oneSentenceBlurb;
		String description;

		@Override
		// overriding the toString method
		public String toString() {
			return "StoryPitch [id=" + id +", tenativeTitle=" + tenativeTitle + ", expCompletionDate=" + expCompletionDate + ", legnthType="
					+ lengthType + ", oneSentenceBlurb=" + oneSentenceBlurb + ", description=" + description + "]";

		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			//result = prime * result + age;
			result = prime * result + ((description == null) ? 0 : description.hashCode());
			result = prime * result + ((tenativeTitle == null) ? 0 : tenativeTitle.hashCode());
			result = prime * result + id;
			result = prime * result + ((lengthType == null) ? 0 : lengthType.hashCode());
			result = prime * result + ((oneSentenceBlurb == null) ? 0 : oneSentenceBlurb.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			StoryPitch other = (StoryPitch) obj;
			if (expCompletionDate != other.expCompletionDate)
				return false;
			if (description == null) {
				if (other.description != null)
					return false;
			} else if (!description.equals(other.description))
				return false;
			if (id != other.id)
				return false;
			if (lengthType == null) {
				if (other.lengthType != null)
					return false;
			} else if (!lengthType.equals(other.lengthType))
				return false;
			if (oneSentenceBlurb == null) {
				if (other.oneSentenceBlurb != null)
					return false;
			} else if (!oneSentenceBlurb.equals(other.oneSentenceBlurb))
				return false;
			if (tenativeTitle == null) {
				if (other.tenativeTitle != null)
					return false;
			} else if (!tenativeTitle.equals(other.tenativeTitle))
				return false;
			return true;
		}

}
