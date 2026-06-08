
### Fixed Window Counter
```
Request : r1 : Amit : Accepted
Request : r2 : Amit : Accepted
Request : r3 : Amit : Rejected
Request : r4 : Amit : Accepted
```

### Sliding Window Log Algo
```
Request : r1 : Amit : Accepted
Request : r2 : Amit : Accepted
Request : r3 : Amit : Rejected
Request : r4 : Amit : Accepted
PS D:\7.Git-Projects\RateLimiter>
```

### Token Bucket
```
Request : r1 : Amit : Accepted
Request : r2 : Amit : Rejected
Request : r3 : Amit : Accepted
Request : r4 : Amit : Accepted
PS D:\7.Git-Projects\RateLimiter>
```


### Leaky Bucket
```
Request : r1 : Amit : Accepted
Request : r2 : Amit : Accepted
Request : r1 : Amit : Leaked
Request : r2 : Amit : Leaked
Request : r3 : Amit : Accepted
Request : r3 : Amit : Leaked
Request : r4 : Amit : Accepted
PS D:\7.Git-Projects\RateLimiter> 
```
