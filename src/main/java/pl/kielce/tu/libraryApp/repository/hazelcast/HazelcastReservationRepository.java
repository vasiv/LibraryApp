package pl.kielce.tu.libraryApp.repository.hazelcast;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.flakeidgen.FlakeIdGenerator;
import com.hazelcast.map.IMap;
import com.hazelcast.query.Predicate;
import com.hazelcast.query.Predicates;
import pl.kielce.tu.libraryApp.model.Reservation;
import pl.kielce.tu.libraryApp.model.User;
import pl.kielce.tu.libraryApp.repository.ReservationRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ciepluchs
 */
public class HazelcastReservationRepository implements ReservationRepository {

    private static final String RESERVATION = "reservation";
    private static final String RESERVATION_ID = "reservationId";
    private static final String RESERVATION_OWNER = "reservationOwner";
    private IMap<Long, Reservation> map;
    private FlakeIdGenerator idGenerator;

    public HazelcastReservationRepository(HazelcastInstance hazelcastInstance) {
        map = hazelcastInstance.getMap(RESERVATION);
        idGenerator = hazelcastInstance.getFlakeIdGenerator(RESERVATION_ID);
    }

    @Override
    public void add(Reservation reservation) {
        map.put(idGenerator.newId(), reservation);
    }

    @Override
    public List<Reservation> getUserReservations(User user) {
        Predicate<String, User> reservationOwnerPredicate = Predicates.equal(RESERVATION_OWNER, user);
        return new ArrayList<>(map.values(Predicates.and(reservationOwnerPredicate)));
    }

}
