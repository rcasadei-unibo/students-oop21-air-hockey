package physics;

import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;

public class PlayerBody extends RigidBodyImpl {
	
	/**
	 * Player body generator
	 * @param radius
	 * @param pos Position to be spawned
	 * @param world
	 */
    public PlayerBody(final float radius, final Vec2 pos, final World world) {
    	setWorld(world);
        setBodyTypeDef(BodyType.DYNAMIC);
        setPositionDef(pos);
        setLinearDampingDef(0.1f);
        configBodyDef();
        
        CircleShape shape = new CircleShape();
        shape.m_radius = radius;
        
        FixtureDef fixture = new FixtureDef();
        fixture.shape = shape;
        fixture.density = 0.4f;
        
        // Bit mask for mid arena fixture collision
        fixture.filter.categoryBits = 0x0002;
        
        Body playerBody = world.createBody(getBodyDef());
        playerBody.createFixture(fixture);
        setBody(playerBody);
    }
    
    // Methods only for Enemy IA
    
    /**
     * Apply a force to the player body.
     * @param force to be applied to the body.
     */
    public void ApplyForce(final Vec2 force) {
        getBody().applyForceToCenter(force);
    }
    
}
