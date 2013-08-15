package ar.com.carloscurotto.hashing.consistent;

import com.google.common.hash.Hasher;
import com.google.common.hash.Hashing;

public class MD5HashFunction implements HashFunction {

    @Override
    public Integer hash(Object key) {
        Hasher hasher = Hashing.md5().newHasher();
        hasher.putString(key.toString());
        return hasher.hash().asInt();
    }

}
