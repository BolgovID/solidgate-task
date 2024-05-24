Possible improvement:
1. If the input map size is bigger than some fixed value, we can make update operation in parallel using kotlin coroutines.
I would use strategy pattern for size check.
2. We can switch to native sql to improve efficiency.