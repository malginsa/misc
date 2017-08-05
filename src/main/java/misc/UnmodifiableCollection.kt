package misc

import java.util.*

object UnmodifiableCollection {

    @JvmStatic fun main(args: Array<String>) {

        val list = ArrayList<Int>()

        list.add(1)
        list.add(2)
        list.add(3)

        @SuppressWarnings
        val unmodifiableCollection = Collections.unmodifiableCollection(list)

        //        unmodifiableCollection.add(4); // throws UnsupportedOperationException

    }

}
